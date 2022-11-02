package ru.graphorismo.coffeeshop.data.remote

class NetworkException(override val message: String?):
    RuntimeException(message) {

}