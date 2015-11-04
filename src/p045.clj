(ns p045)

(defn- h [n] (* n (- (* 2 n) 1)))

(defn- pentagonal? [x]
  (let [n (-> (* 24 x) (+ 1) (Math/sqrt) (+ 1) (/ 6))]
    (== n (int n))))

;; T(2n-1) = H(n), which means every H(n) is also a triangle number.
;; So, check there exists a integer n that satisfies 3n^2-n-2h = 0
;; for a given hexagonal h.
(defn solve []
  (->> (iterate inc 144) ; start from H(144)
       (map h)
       (filter pentagonal?)
       first))
