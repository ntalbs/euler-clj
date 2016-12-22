(ns p081
  (:require [util :refer [parse-int]]))

(def m
  (->> (clojure.string/split (slurp "data/matrix.txt") #"\r\n")
       (mapv (fn [line] (mapv parse-int (clojure.string/split line #","))))))

(defn- t
  "returns the lists of positions of elemements in diagonal order
   (/ direction) from right-bottom to left-top."
  [n]
  (concat (for [i (range n)]
            (for [j (range (inc i))]
              [(- n j 1) (+ (- n i 1) j)]))
          (for [i (range (dec n))]
            (for [j (range (- (dec n) i))]
              [(- (dec n) j i 1) j]))))

(defn- mg [xs ys]
  (flatten (vector (first xs)
                   (map min (rest xs) ys)
                   (last ys))))

(defn- min-sum [xs ys]
  (if (< (count xs) (count ys))
    (mg (map + xs ys) (map + xs (rest ys)))
    (map min (map + xs ys) (map + (rest xs) ys))))

(defn p081 [m]
  (->> (map (fn [ks] (for [k ks] (get-in m k))) (t (count m)))
       (reduce min-sum)
       first))

(defn solve []
  (time (println (p081 m))))
