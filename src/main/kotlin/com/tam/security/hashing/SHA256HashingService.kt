package com.tam.security.hashing

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.security.SecureRandom

const val DEFAULT_SALTING_ALGORITHM = "SHA1PRNG"

class SHA256HashingService : HashingService {
    override fun generateSaltedHash(value: String, saltLength: Int): SaltedHash {
        val salt = SecureRandom.getInstance(DEFAULT_SALTING_ALGORITHM).generateSeed(saltLength)
        val saltAsHex = Hex.encodeHexString(salt)
        val hash = DigestUtils.sha256Hex("$saltAsHex$value")

        return SaltedHash(
            hash = hash,
            salt = saltAsHex
        )
    }

    override fun verify(value: String, saltedHash: SaltedHash): Boolean =
        DigestUtils.sha256Hex("${saltedHash.salt}$value") == saltedHash.hash

}
