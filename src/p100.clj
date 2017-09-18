;; n = b + r, b: # of blue balls, r: # of red balls
;; b(k+1) = 3b(k) + 2n(k) - 2
;; n(k+1) = 4b(k) + 3n(k) - 3

(ns p100)

(defn next-b [b n] (+ (* 3 b) (* 2 n) -2))
(defn next-n [b n] (+ (* 4 b) (* 3 n) -3))

(def target 1000000000000)

(defn solve []
  (loop [b 15, n 21]
    (if (< n target)
      (recur (next-b b n) (next-n b n))
      b)))
