# Test online shop

Приложение, имитирующее онлайн магазин. Данные о товарах берутся с json файла онлайн.

[.apk файл](https://github.com/Garshishka/TestOnlineShop/blob/master/app-debug.apk)

## Стек
* Язык Kotlin
* MVVM
* Kotlin Coroutines
* LiveData
* Dagger 
* XML верстка
* Room

## Состав приложения

Экраны представлены фрагментами

* [Экран регистрации](https://github.com/Garshishka/TestOnlineShop/blob/master/app/src/main/java/ru/garshishka/testonlineshop/ui/RegistrationFragment.kt) [(xml)](https://github.com/Garshishka/TestOnlineShop/blob/master/app/src/main/res/layout/fragment_registration.xml) - ввод имени, фамилии и телефона. При повторном запуске пропускается
* [Экран каталога](https://github.com/Garshishka/TestOnlineShop/blob/master/app/src/main/java/ru/garshishka/testonlineshop/ui/CatalogueFragment.kt) [(xml)](https://github.com/Garshishka/TestOnlineShop/blob/master/app/src/main/res/layout/fragment_catalogue.xml) - отображение всех товаров, по клику на товар переходит на экран одного продукта
* [Экран одного продукта](https://github.com/Garshishka/TestOnlineShop/blob/master/app/src/main/java/ru/garshishka/testonlineshop/ui/ProductFragment.kt) [(xml)](https://github.com/Garshishka/TestOnlineShop/blob/master/app/src/main/res/layout/fragment_product.xml) - отображает кликнутый ранее товар товар
* [Экран личного кабинета](https://github.com/Garshishka/TestOnlineShop/blob/master/app/src/main/java/ru/garshishka/testonlineshop/ui/ProfileFragment.kt) [(xml)](https://github.com/Garshishka/TestOnlineShop/blob/master/app/src/main/res/layout/fragment_profile.xml) - содержит информацию о пользователе, введеную ранее
* [Экран избранного](https://github.com/Garshishka/TestOnlineShop/blob/master/app/src/main/java/ru/garshishka/testonlineshop/ui/FavoritesFragment.kt) [(xml)](https://github.com/Garshishka/TestOnlineShop/blob/master/app/src/main/res/layout/fragment_favorites.xml) - отображает товары, добавленные пользователем в избранное. по клику на товар переходит на экран одного продукта
* Экраны заглушки для основной страницы, корзины и скидок

Все фрагменты находятся в [основной Activity](https://github.com/Garshishka/TestOnlineShop/blob/master/app/src/main/java/ru/garshishka/testonlineshop/MainActivity.kt) [(xml)](https://github.com/Garshishka/TestOnlineShop/blob/master/app/src/main/res/layout/activity_main.xml), которая содержит меню навигации и заголовок.
