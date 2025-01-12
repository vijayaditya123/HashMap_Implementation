import java.util.*;
public class JavaBasics{
    public static class HashMap<K,V>{
       private  class Node {
        K key;
        V value;
        public Node(K key ,V value){
            this.key = key;
            this.value= value;
        }
       }
       private int n;
       private int N;
       private LinkedList<Node> bukets[];
       public HashMap(){
        this.N=4;
        this.bukets = new LinkedList[N];
        for(int i=0;i<bukets.length;i++){
            bukets[i] = new LinkedList<>();
        }
       }
       public int hashfunction(K key){
             int hi = key.hashCode();
            return Math.abs(hi)% bukets.length;
       }
       public  int searchinll(int bi,K key){
          LinkedList<Node> ll = bukets[bi];
        for(int i=0;i<ll.size();i++){
            Node node = ll.get(i);
            if( node.key== key){
                return i;
            }
        }
        return -1;

       }
       public void rehash(){
          LinkedList<Node> copyarr[] =  bukets;
          N= N*2;
          bukets = new LinkedList[N];
          for(int i=0;i<bukets.length;i++){
            bukets[i] = new LinkedList();
          }
          for(int i=0;i<copyarr.length;i++){
            LinkedList<Node> ll = copyarr[i];;
            for(int j=0;j<ll.size();i++){
                Node node = ll.get(j);
                put(node.key,node.value);
            }

          }
       }
       public void put(K key,V value){
        int bi = hashfunction(key);
        int si = searchinll(bi,key);
        if(si != -1){
            Node node = bukets[bi].get(si);
            node.value = value;
        }else{
              bukets[bi].add(new Node(key, value));
              n++;
        }
        double lambda= (double) n/N;
         if(lambda > 2){
            rehash();
         }
       }
       public boolean contain(K key){
          int bi = hashfunction(key);
           int di = searchinll(bi, key);
          
            if(di != -1){
                return true;
            
           }else{
           return false;}
       }
     public V get(K key){
        int bi = hashfunction(key);
        int di = searchinll(bi, key);
        if(di != -1){
        Node  node = bukets[bi].get(di);
        return node.value;
        }else{
            return null;
        }
     }
     public V remove(K key){
        int bi = hashfunction(key);
        int di = searchinll(bi, key);
        if(di != -1){
        Node  node = bukets[bi].remove(di);
        n--;
        return node.value;
        }else{
            return null;
        }
     }
     public ArrayList<K> keySet(){
        ArrayList<K> keys =new ArrayList<>();
       for(int i =0;i<bukets.length;i++){
        LinkedList<Node> ll= bukets[i];
        for(Node node: ll){
          keys.add(node.key);
        }
        
    }
      return keys;
     }
    }
    public static void main(String args[]) {
        HashMap<String,Integer> hm =  new HashMap<>();
        hm.put("India", 150);
        hm.put("Us", 50);
        hm.put("Nepal", 25);
        hm.put("china", 160 );
        hm.put("punjab", 36);
        
        
        ArrayList<String> keys = hm.keySet();
        for(String key : keys ){
            System.out.println(hm.get(key));
        }
    }
}
