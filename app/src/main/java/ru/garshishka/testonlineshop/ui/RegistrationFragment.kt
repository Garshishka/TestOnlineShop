package ru.garshishka.testonlineshop.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import ru.garshishka.testonlineshop.R
import ru.garshishka.testonlineshop.databinding.FragmentRegistrationBinding
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private var nameFilledRight = false
    private var surnameFilledRight = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUi()
    }

    private fun setUpUi() {
        binding.apply {
            inputName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(s: Editable) {
                    nameFilledRight = inputName.changeColorIfInputWrong(s)
                    checkButtonStatus()
                }
            })

            inputSurname.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(s: Editable) {
                    surnameFilledRight = inputSurname.changeColorIfInputWrong(s)
                    checkButtonStatus()
                }
            })

            val watcher: FormatWatcher =
                MaskFormatWatcher(MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER))
            watcher.installOn(inputPhone)
            inputPhone.addTextChangedListener { checkButtonStatus() }

            enterButton.setOnClickListener {
                //TODO Save inputted
                findNavController().navigate(R.id.action_registrationFragment_to_catalogueFragment)
            }
        }
    }

    private fun TextInputEditText.changeColorIfInputWrong(s: Editable): Boolean {
        val errorColor = requireContext().getColor(R.color.light_pink)
        val normalColor = requireContext().getColor(R.color.light_grey_bg)

        val cyrillicPattern = Regex("[А-Яа-я]+")

        this.setBackgroundColor(if (cyrillicPattern.matches(s.toString())) normalColor else errorColor)
        return cyrillicPattern.matches(s.toString())
    }

    private fun checkButtonStatus() {
        binding.apply {
            enterButton.isEnabled = nameFilledRight && surnameFilledRight
                    && !inputName.text.isNullOrBlank() && !inputSurname.text.isNullOrBlank()
                    && inputPhone.text?.length == 18
        }
    }
}