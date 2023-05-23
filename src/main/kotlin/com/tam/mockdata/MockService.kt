package com.tam.mockdata

class MockService(private val repository: TestRepository) {
    fun getMockData(): MockData = repository.getMockData()
}