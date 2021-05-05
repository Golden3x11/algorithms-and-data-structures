public class Test {
    public static void main(String[] args) throws IQueue.FullQueueException, IQueue.EmptyQueueException {
        int[] array={2,4,6,7,1,5,2};
        HeapPriorityQueue heap=new HeapPriorityQueue(array);
        heap.createHeap(array.length);
        for(Integer integer:array)
            System.out.println(integer);

    }
}
