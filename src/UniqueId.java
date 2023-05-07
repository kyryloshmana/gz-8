public class UniqueId implements iUniqueId {
    @Override
    public Integer uniqueDataId() {
        int n = 100000;
        return (int) (Math.random() * (n + 1));
    }


}