public class Block {
    private BlockHeader header;
    private BlockData data;
    //private int hash;

    public Block(BlockHeader header, BlockData data) {
        this.header = header;
        this.data = data;
        //this.hash = hashCode(getHeader(), getData());
    }

    public BlockData getData() {
        return data;
    }

    public void setData(BlockData data) {
        this.data = data;
    }

    public BlockHeader getHeader() {
        return header;
    }

    public void setHeader(BlockHeader header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "Block{" +
                "header=" + header +
                ", data=" + data +
                '}';
    }

}