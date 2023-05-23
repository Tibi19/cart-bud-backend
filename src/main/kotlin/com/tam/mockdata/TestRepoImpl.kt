package com.tam.mockdata

class TestRepoImpl : TestRepository {
    override fun getMockData(): MockData {
        return MockData("Markus", "A chill boi that likes to mock stuff, especially data")
    }
}