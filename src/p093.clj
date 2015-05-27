(ns p093
  (:require [clojure.math.combinatorics :refer [permutations combinations]]))

;; 일단 답은 구하지만 target 함수가 정말 모든 경우를 고려하고 있는지는 검토가 필요함.

(defn- target [[a b c d]]
  (let [op [+ - * /]]
    (->> (for [f1 op, f2 op, f3 op]
           [(-> (f1 a b) (f2 c) (f3 d))
            (-> (f3 (f1 a b) (f2 c d)))])
         (flatten))))

(defn- gen [[a b c d]]
  (->> (permutations [a b c d])
       (mapcat #(target %))
       (distinct)
       (filter #(and (pos? %) (integer? %)))
       (sort)
       (hash-map :set [a b c d] :target)))

(defn- count-consective [ns]
  (->> (partition 2 1 ns)
       (take-while (fn [[a b]] (= a (dec b))))
       (count)
       (inc)))

(defn solve []
  (->> (combinations (range 1 10) 4)
       (map gen)
       (apply max-key (fn [x] (count-consective (:target x))))
       (:set)))
