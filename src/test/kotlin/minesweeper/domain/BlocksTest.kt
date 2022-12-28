package minesweeper.domain

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

/**
 * @see Blocks
 */
class BlocksTest : ExpectSpec({

    context("생성 성공 케이스") {
        expect("전체 블록 개수와 지뢰 개수를 입력하면 지뢰 개수만큼 지뢰가 포함된 블록을 만들어준다") {
            val blockCount = 9
            val mineCount = 3

            val blocks = Blocks.of(blockCount, mineCount)

            assertSoftly {
                blocks.blocks.size shouldBe 9
                blocks.blocks.count { it.javaClass == CleanBlock::class.java } shouldBe 6
                blocks.blocks.count { it.javaClass == MineBlock::class.java } shouldBe 3
            }
        }
    }

    context("생성 실패 케이스") {
        expect("전체 블록 개수보다 많은 지뢰 개수를 입력하면 생성되지 않는다") {
            val blockCount = 9
            val mineCount = 10

            shouldThrowExactly<IllegalArgumentException> {
                Blocks.of(blockCount, mineCount)
            }
        }
    }
})
