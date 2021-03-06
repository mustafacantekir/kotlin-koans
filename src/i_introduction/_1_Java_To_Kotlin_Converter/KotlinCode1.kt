package i_introduction._1_Java_To_Kotlin_Converter

class KotlinCode1 {

    fun task1(collection: Collection<Int>): String {
        val sb = StringBuilder()
        sb.append("{")
        val iterator = collection.iterator()
        while (iterator.hasNext()) {
            val element = iterator.next()
            sb.append(element)
            if (iterator.hasNext()) {
                sb.append(", ")
            }
        }
        sb.append("}")
        return sb.toString()
    }
}