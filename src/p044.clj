(ns p044)

(defn- p
  "Returns a n-th pentagonal number. P(n) = n (3n−1)/2"
  [n]
  (/ (* n (- (* 3 n) 1)) 2))

(defn- pentagonal? [x]
  (let [n (-> (* 24 x) (+ 1) (Math/sqrt) (+ 1) (/ 6))]
    (== n (int n))))

(defn solve []
  (first (for [i (range) j (range 1 i)
               :let [pi (p i) pj (p j)]
               :when (pentagonal? (- pi pj))
               :when (pentagonal? (+ pi pj))]
           (- pi pj))))
