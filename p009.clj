;; #009
;; There exists exactly one Pythagorean triplet for which a+b+c=1000.
;; Find the product abc.

; initial: fairly fast when a+b+c is small, but not usable when a+b+c is large.
(def p009
  (first (for [a (range 1 1000)
               b (range a 1000)
               c [(- 1000 a b)]
               :when (= (+ (* a a) (* b b)) (* c c))]
           (* a b c))))

(time (println p009))

; improved: TODO
