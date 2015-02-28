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
  (let [cnt (count network)]
    (->> (for [i (range cnt) j (range (inc i) cnt)
               :let [w (get-in network [i j])]
               :when (not= nil w)]
           [i j w])
         (sort-by (fn [[_ _ w]] w)))))

(def vertex-partition (map (comp set list) (range 40)))

(defn partition-merge [part a b]
  (conj (filter #(not (or (contains? % a)(contains? % b))) part)
        (apply clojure.set/union
               (concat (filter #(contains? % a) part)
                       (filter #(contains? % b) part)))))

(defn add-link [[partition tree] link]
  (let [new (partition-merge partition (first link) (second link))]
    (if (< (count new) (count partition))
      [new (cons link tree)]
      [new tree])))

(defn p107 []
  (let [tree (second (reduce add-link [vertex-partition '()] edges))
        weight (reduce + (map (fn[[_ _ x]] x) tree))
        total-weight (reduce + (map (fn [[_ _ w]] w) edges))]
    (- total-weight weight)))

(defn solve []
  (time (println (p107))))
