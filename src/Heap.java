public class Heap {

    private int[] _heap;
    private int _length;


    public Heap(int[] arr, int len){
        _length = len;
        _heap = arr;
        if (len > 0) {
            for (int i = _length / 2; i >= 0; i--)
                heapify(this, i);
        }
    }

    private static void heapify(Heap heap, int index) {
        if(index<0 || index>=heap._length)
            return;
        int l = left(index), r=right(index), ll = left(left(index)), lr = right(left(index)), smallest =index;
        int rr = right(right(index)), rl = left(right(index)), largest = index;
        if (height(index) % 2 == 0) {
            if(r< heap._length && heap._heap[r]>heap._heap[largest])
                largest = r;
            if(l< heap._length && heap._heap[l]>heap._heap[largest])
                largest = l;
            if(ll< heap._length && heap._heap[ll]>heap._heap[largest])
                largest = ll;
            if(lr< heap._length && heap._heap[lr]>heap._heap[largest])
                largest = lr;
            if(rr< heap._length && heap._heap[rr]>heap._heap[largest])
                largest = rr;
            if(rl< heap._length && heap._heap[rl]>heap._heap[largest])
                largest = rl;
            if(largest != index) {
                swap(heap._heap, index, largest);

                if (heap._length>3 && grandchild(index, largest)) {
                    if (heap._heap[largest] < heap._heap[father(largest)])
                        swap(heap._heap, largest, father(largest));

                }
                heapify(heap, largest);
            }

        }

        else {
            if(r< heap._length && heap._heap[r]<heap._heap[smallest])
                smallest = r;
            if(l< heap._length && heap._heap[l]<heap._heap[smallest])
                smallest = l;
            if(ll< heap._length && heap._heap[ll]<heap._heap[smallest])
                smallest = ll;
            if(lr< heap._length && heap._heap[lr]<heap._heap[smallest])
                smallest = lr;
            if(rr< heap._length && heap._heap[rr]<heap._heap[smallest])
                smallest = rr;
            if(rl< heap._length && heap._heap[rl]<heap._heap[smallest])
                smallest = rl;
            if(smallest != index) {
                swap(heap._heap, index, smallest);
                if(heap._length>3 && grandchild(index,smallest)){
                    if(heap._heap[smallest] > heap._heap[father(smallest)])
                        swap(heap._heap, smallest,father(smallest));

                }
                heapify(heap, smallest);
            }

        }


    }

    public static void delete(Heap heap, int index){
        int len= heap._length;
        swap(heap._heap, index,len-1);
        heap._length=len-1;
        heapify(heap,index);
    }

    public static void insert(Heap heap, int value){
        int len= heap._length;
        heap._heap[len]=value;
        heap._length=len+1;
        heap._length=len+1;
        bubbleUp(heap,len);

    }

    //sort heap
    public static void sort(Heap heap){
        int[] arr = new int[256];
        int len = heap._length;
        for(int i=0; i< len; i++){
            arr[i] = extractMin(heap);
        }
        heap._heap=arr;
        heap._length=len;
    }

    public static int extractMax(Heap heap){
        int len=heap._length;
        int max = heap._heap[0];
        heap._heap[0] = heap._heap[len-1];
        heap._heap[len-1] = max;
        heap._length = len-1;
        heapify(heap,0);
        return max;
    }

    public static int extractMin(Heap heap){
        int len=heap._length;
        switch (len){
            case 1: return heap._heap[0];
            case 2: heap._length--;
                    return heap._heap[1];
        }
        int index = heap._heap[1] < heap._heap[2] ? 1:2;
        int min = heap._heap[index];
        heap._heap[index] = heap._heap[len-1];
        heap._heap[len-1] = min;
        heap._length = len-1;
        heapify(heap,index);
        return min;
    }

    private static void bubbleUp(Heap heap, int index){
        int father = father(index), grandFather = father(father(index));
        if(index<=0)
            return;
        if(height(index)%2==0){
            if(heap._heap[index]<heap._heap[father]) {
                swap(heap._heap, index, father(index));
                bubbleUp(heap,father);
            }
            else if(grandFather!=father && heap._heap[index]>heap._heap[grandFather]) {
                swap(heap._heap, index, grandFather);
                bubbleUp(heap,grandFather);
            }
        }
        else{
            if(heap._heap[index]>heap._heap[father]) {
                swap(heap._heap, index, father(index));
                bubbleUp(heap,father);
            }
            else if(grandFather!=father && heap._heap[index]<heap._heap[grandFather]) {
                swap(heap._heap, index, grandFather);
                bubbleUp(heap,grandFather);
            }
        }

    }

    public String toString(){
        String str ="";
        int i;
        if(_length >0) {
            for (i = 0; i < _length - 1; i++) {
                str += _heap[i] + ", ";
            }
            str += +_heap[i];
        }
        return str;
    }

    //check if between two index there is a grandfather grandchild relationship
    private static boolean grandchild(int father, int child){
        for(int i=0; i<=1; i++) {
            if (child % 2 == 0)
                child--;
            child = child / 2;
        }
        return child == father;
    }

    //calculate height of the index
    public static int height(int val){
        return (int)(Math.log(val+1)/Math.log(2));
    }
    //swap to elements in an array
    private static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    //return who is the father of index
    private static int father(int index){
        if(index%2==0)
            return (index-1)/2;
        return index/2;
    }
    //return who is the right son of index
    private static int right(int index){
        return (index+1)*2;
    }
    //return who is the left son of index
    private static int left(int index){
        return ((index+1)*2)-1;
    }
    //return length
    public int getLength(){
        return _length;
    }


}
