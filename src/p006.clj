;; difference between the sum of squares of the first one hundred natural numbers
;; and the square of the sum

(ns p006)

; initial: brute force
(defn using-brute-force [n]
  (let [s (range 1 (inc n))
        sum-of-sq (reduce + (map (fn [n] (* n n)) s))
        sq-of-sum (let [sum (reduce + s)]
                    (* sum sum))]
    (- sq-of-sum sum-of-sq)))


; improved: using formula.
; sum(n) = n(n+1)/2
; sumsq(n) = n(n+1)(2n+1)/6
(defn sq-of-sum [n]
  (let [s (/ (* n (+ n 1)) 2)]
    (* s s)))

(defn sum-of-sq [n]
  (/ (* n (+ n 1) (+ (* 2 n) 1)) 6))

(defn using-formula [n]
  (- (sq-of-sum n) (sum-of-sq n)))

(defn solve []
  (time (println "initial => " (using-brute-force 100)))
  (time (println "improved => " (using-formula 100))))
