;; #004
;; largest palindrome made from the product of two 3-digit numbers

(defn palindrome? [n]
  (= (str n) (apply str (reverse (str n)))))

; initial
(defn p004-1 []
  (->> (for [a (range 100 1000) b (range a 1000)] (* a b))
       (filter palindrome?)
       (apply max)))

(time (println "initial => " (p004-1)))

; improved
; P = ab = 100000x + 10000y + 1000z + 100z + 10y + x
;        = 100001x + 10010y + 1100z
;        = 11(9091x + 910y + 100z)
; 11 is prime, a or b must have a factor of 11
(defn p004-2 []
  (->> (for [a (range 100 1000) b (range a 1000)
             :when (or (= 0 (mod a 11)) (= 0 (mod b 11)))]
         (* a b))
       (filter palindrome?)
       (apply max)))

(time (println "improved => " (p004-2)))
