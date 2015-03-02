;; http://en.wikipedia.org/wiki/Kruskal's_algorithm
;; http://en.wikipedia.org/wiki/Disjoint-set_data_structure
;; http://www.learningclojure.com/2013/09/kruskals-algorithm-for-minimal-spanning.html

(ns p107)

(defn- parse-weight [s]
  (if (= "-" s) nil (Integer/parseInt s)))

(def network
  (->> (slurp "data/p107_network.txt")
       (clojure.string/split-lines)
       (mapv #(clojure.string/split % #","))
       (mapv #(mapv parse-weight %))))

(def edges
  "edges of graph order by its weight."
  (let [cnt (count network)]
    (->> (for [i (range cnt) j (range (inc i) cnt)
               :let [w (get-in network [i j])]
               :when (not= nil w)]
           [i j w])
         (sort-by (fn [[_ _ w]] w)))))

(def ds
  "singletons of vertexes."
  (map (comp set list) (range 40)))

(defn find-set
  "find a subset that contains v in ds."
  [ds v]
  (first (filter #(contains? % v) ds)))

(defn union
  "join two subsets into one."
  [ds u v]
  (let [s1 (find-set ds u), s2 (find-set ds v)]
    (conj (remove #(or (contains? % u) (contains? % v)) ds)
          (clojure.set/union s1 s2))))

(defn add-link
  "add edge to mst(minimal spanning tree)
  if the edge isn't contained the subsets of each vertexes of the edge."
  [[ds mst] edge]
  (let [[u v _] edge]
    (if (not= (find-set ds u) (find-set ds v))
      [(union ds u v) (conj mst edge)]
      [ds mst])))

(defn kruskal [ds links]
  (second (reduce add-link [ds []] links)))

(defn sum-weight [edges]
  (reduce + (map (fn [[_ _ w]] w) edges)))

(defn p107 []
  (let [mst (kruskal ds edges)
        weight-mst (sum-weight mst)
        weight-total (sum-weight edges)]
    (- weight-total weight-mst)))

(defn solve []
  (time (println (p107))))
