;; #006
;; difference between the sum of squares of the first one hundred natural numbers
;; and the square of the sum

(ns p006)

; initial: brute force
(defn p006-1 []
  (let [s (range 1 101)
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
(defn p006-2 [] (- (sq-of-sum 100) (sum-of-sq 100)))

(defn solve []
  (time (println "initial => " (p006-1)))
  (time (println "improved => " (p006-2))))
