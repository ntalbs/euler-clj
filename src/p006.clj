;; #006
;; difference between the sum of squares of the first one hundred natural numbers
;; and the square of the sum

; initial: brute force
(def s (range 1 101))
(def sum-of-sq
  (reduce + (map (fn [n] (* n n)) s)))
(def sq-of-sum
  (let [sum (reduce + s)]
    (* sum sum)))
(defn p006-1 [] (- sq-of-sum sum-of-sq))

(time (println "initial => " (p006-1)))

; improved: using formula.
; sum(n) = n(n+1)/2
; sumsq(n) = n(n+1)(2n+1)/6
(defn sq-of-sum [n]
  (let [s (/ (* n (+ n 1)) 2)]
    (* s s)))
(defn sum-of-sq [n]
  (/ (* n (+ n 1) (+ (* 2 n) 1)) 6))
(defn p006-2 [] (- (sq-of-sum 100) (sum-of-sq 100)))

(time (println "improved => " (p006-2)))
