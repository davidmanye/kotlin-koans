package i_introduction._3_Default_Arguments

object KotlinCode3 {

    fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false): String {
        return (if (toUpperCase) name.toUpperCase() else name) + number
    }

}