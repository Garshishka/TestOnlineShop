package ru.garshishka.testonlineshop

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.garshishka.testonlineshop.databinding.FragmentRegistrationBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

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

        val afterTextChangedNumberListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }
            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {
                    if (s.length == 1) {
                        if (s.toString() == "+" || s.toString() == "7" || s.toString() == "8"
                        ) {
                            s.replace(0, s.length, "+7 ")
                        } else {
                            val keep = s.toString()
                            s.clear()
                            s.append("+7 $keep")
                        }
                    }
                    when (s.length) {
                        6 -> s.append(" ")
                        10 -> s.append(" - ")
                        15 -> s.append(" - ")
                        20 -> binding.enterButton.isEnabled = true
                    }
                    binding.enterButton.isEnabled = s.length == 20

                    if (s.length > 20) {
                        val keep = s.toString().substring(0, 20)
                        s.clear()
                        s.append(keep)
                    }
                }
            }
        }
        binding.inputPhone.addTextChangedListener(afterTextChangedNumberListener)
    }
}