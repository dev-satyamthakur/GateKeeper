package com.satyamthakur.gatekeeper.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "flag_rules")
class FlagRule(

    @Id
    @GeneratedValue
    val id: UUID? = null,

    val flagName: String,

    val attribute: String,   // country, device, email
    val operator: String,    // equals, contains
    val value: String
)