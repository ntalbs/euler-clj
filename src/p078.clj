;; http://en.wikipedia.org/wiki/Partition_(number_theory)#Partition_function_formulas
;; http://en.wikipedia.org/wiki/Pentagonal_number_theorem

(ns p078)

(defn- s [k] (if (even? k) -1 1))

(defn- g [k] (quot (* k (dec (* 3 k))) 2))

(def ^:private gs
  (->> (interleave (iterate inc 1) (iterate dec -1))
       (map (fn [k] [(s k) (g k)]))))

(defn- p [n]
  (cond (< n 0) 0
        (= n 0) 1
        (= n 1) 1
        (= n 2) 2
        (= n 3) 3
        :else (->> gs
                   (take-while #(<= (second %) n))
                   (map (fn [[s g]] (mod (* s (p (- n g))) 1000000)))
                   (apply +))))

(def p (memoize p))

(defn solve []
  (->> (range)
       (map #(vector % (p %)))
       (drop-while #(not= 0 (mod (second %) 1000000)))
       ffirst))
