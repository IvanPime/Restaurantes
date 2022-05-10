package com.diplomado.laboratorio2.model

data class Restaurante(
    var nombre: String,
    var calificacion: Float,
    var anio: Integer,
    var costoPromedio: Float,
    var direccion: String,
    var ubicacion: String,
    var resenias: List<Resenia>,
    var imagenes: List<String>
)