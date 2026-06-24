import java.util.*;
public class HashMapCode {
    //hash map data structure
    static class HashMap<K,V>{ //generic 
        private class Node{ 
            K key;
            V value;

            public Node(K key, V value){
                this.key = key;
                this.value = value;
            }
        }

        private int n; //nodes (total)
        private int N; // array size
        private LinkedList<Node> buckets[]; //N = buckets.length

        @SuppressWarnings("unchecked")
// uper vala HashMap ka construtor
        public HashMap(){
            this.N = 4;
            this.buckets = new LinkedList[4];
            for(int i=0; i<4; i++){
                //har buckets par jake ak linkedlist banayege
                this.buckets[i] = new LinkedList<>();
            }
        }

        private int hashFunction(K key){
            //hashcode convert integer values
            //key ki form ko change kar k integer values dega(ex.2635600)
            int bi = key.hashCode();
            //both values dega to haye me hamesha positive values chahiye
            return Math.abs(bi) % N;  //(abs :- absolute)
        }

        private int searchInLL(K key,int bi){
            LinkedList<Node> ll = buckets[bi];
            for(int i=0; i<ll.size(); i++){

   //Bug haye kyu ki string comper kaene k liye equals ata haye (==)
                if(ll.get(i).key == key){
                    return i;
                }
            }
            return -1;
        }
        @SuppressWarnings("unchecked")
        private void rehash(){  
            LinkedList<Node> oldBucket[] = buckets;
            buckets = new LinkedList[N*2];

            for(int i=0; i<N*2; i++){
                buckets[i] = new LinkedList<>();    
            }

            for(int i=0; i<oldBucket.length; i++){
                LinkedList<Node> ll = oldBucket[i];
                for(int j=0; j<ll.size(); j++){
                    Node node = ll.get(j);
                    put(node.key, node.value);
                }
            }
        }
        public void put(K key, V value){
            int bi = hashFunction(key);
            int di = searchInLL(key, bi); // data index 

            if(di == -1){ //key doesn't exist
            //key nahi haye to use bi(bucket index to store karege)
            buckets[bi].add(new Node(key, value));
            //add kiya haye matlb di ka size badega
            n++;
            }else{ //key exist
               Node node = buckets[bi].get(di);
               //data.values ko aple value se compare karege ki vo dono same haye
                node.value = value;
            }

            //add karne k baad check karege ki lambda ki value high to nahi ho gai
            double lambda = (double)n/N;
            if(lambda > 2.0){
                rehash();
            }
        }

        public boolean containsKey(K key){
             int bi = hashFunction(key);
            int di = searchInLL(key, bi); // data index 

            if(di == -1){ 
                return false;
            }else{ //key exist
               return true;
            }
        }

        public V remove(K key){
             int bi = hashFunction(key);
            int di = searchInLL(key, bi); // data index 

            if(di == -1){ //key doesn't exist
            return null;
            }else{ //key exist
               Node node = buckets[bi].remove(di);
               n--;
                return node.value;
            }
        }
        public V get(K key){
             int bi = hashFunction(key);
            int di = searchInLL(key, bi); // data index 

            if(di == -1){ //key doesn't exist
          return null;
            }else{ //key exist
               Node node = buckets[bi].get(di);
               //data.values ko aple value se compare karege ki vo dono same haye
                return node.value;
            }
        }
        public ArrayList<K> KeySet(){
            ArrayList<K> keys = new ArrayList<>();
            for(int i=0; i<buckets.length; i++){
                LinkedList<Node> ll = buckets[i];
                for(int j=0; j<ll.size(); j++){
                    Node node = ll.get(j);
                    keys.add(node.key);
                }   
            }
            return keys;
        }
        public boolean isEmpty(){
            return n == 0;
        }
    }
        public static void main(String args[]) {
            HashMap<String, Integer> map = new HashMap<>();

            map.put("India", 190);
            map.put("Canada", 50);
            map.put("China", 56);
            map.put("Malasia", 45);

            ArrayList<String> keys = map.KeySet();
            for(int i=0; i<keys.size(); i++){
                System.out.println(keys.get(i)+ " "+ map.get(keys.get(i)));
            }
          map.remove("India");
         System.out.println(map.get("India"));

        System.out.println(map.get("China"));
        System.out.println(map.get("Uk"));
        }
    }

