package com.rh.logoquiz.model

class RandomLetterGenerator {

    lateinit var charPool: ArrayList<Char>
    init {
        prepareCharPool()
    }

    private fun prepareCharPool() {
        val charArray : CharArray = "abcdefghijklmnopqrstuvwxyz".toCharArray()
        for(c in charArray){
            charPool.add(c)
        }
    }

    internal fun getListOfRandomLetters(ansString: String?, maxListItems: Int = 20): ArrayList<Char>{
        val ansList = ArrayList<Char>()
        ansString?.let{
            val randomString = (1..maxListItems)
                .map { i -> kotlin.random.Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")

            for (c in randomString.toCharArray()){
                ansList.add(c)
            }
        }
        return ansList
    }
}