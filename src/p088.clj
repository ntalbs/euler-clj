;; https://blog.dreamshire.com/project-euler-88-solution/

(ns p088)

(def kmax 12000)

(defn- prodsum [p s c start n]
  (let [k (+ (- p s) c)]
    (if (< k kmax)
      (do
        (if (< p (@n k))
          (swap! n assoc k p))
        (doseq [i (range start (inc (* (quot kmax p) 2)))]
          (prodsum (* p i) (+ s i) (+ c 1) i n))))
    n))

(defn solve []
  (let [n (atom (vec (repeat kmax (* kmax 2))))]
    (->> @(prodsum 1 1 1 2 n)
         (drop 2)
         (distinct)
         (apply +))))
