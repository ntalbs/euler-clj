(ns p072
  (:require [util :refer [pow factorize]]))

(def limit 1000000)


;; first try
(defn phi
  ([p k]
   (* (pow p k) (- 1 (/ 1 p))))
  ([n]
   (->> (factorize n)
        (map (fn [[p k]] (phi p k)))
        (apply *))))

(defn solve1 []
  (->> (range 2 (inc limit))
       (pmap phi)
       (apply +)))

;; second try
(defn- asum [^ints xs]
  (areduce xs i ret (long 0) (+ ret (aget xs i))))

(defn solve2 []
  (let [phi (int-array (range (inc limit)))]
    (loop [i 2]
      (if (= i (aget phi i))
        (loop [j i]
          (if (<= j limit)
            (do (aset phi j (/ (* (aget phi j) (dec i)) i))
                (recur (+ j i))))))
      (if (< i limit)
        (recur (inc i))))
    (asum phi)))


;; thrid try
(defn solve3 []
  (let [limit 1000000
        phi (int-array (range (inc limit)))]
    (loop [i 2 acc 0]
      (if (= i (aget phi i))
        (loop [j i]
          (if (<= j limit)
            (do (aset-int phi j (/ (* (aget phi j) (dec i)) i))
                (recur (+ j i))))))
      (if (< i limit)
        (recur (inc i) (+ acc (aget phi i)))
        acc))))

(defn solve []
  (solve3))
