package com.example.mynotes.model.authorization

data class RegistrationResponse (var email: String,

                                 var name: String,

                                 var id: Int,

                                 var api_token: String)