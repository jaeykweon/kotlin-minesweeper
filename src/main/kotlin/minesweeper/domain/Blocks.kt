package minesweeper.domain

data class Blocks(
    val blocks: List<Block>
) {

    companion object {
        fun of(wholeCount: Int, mineLocation: List<Int>): Blocks {
            val blocks = (0 until wholeCount).map {
                decideBlock(it, mineLocation)
            }
            return Blocks(blocks)
        }

        private fun decideBlock(location: Int, mineLocation: List<Int>): Block {
            return when (location in mineLocation) {
                true -> MineBlock()
                false -> CleanBlock()
            }
        }
    }
}
