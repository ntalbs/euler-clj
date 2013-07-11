;; #005
;; smallest positive number that is evenly divisible 
;; by all of the numbers from 1 to 20

(defn gcd [a b] (if (= b 0) a (gcd b (rem a b))))
(defn lcm [a b] (/ (* a b) (gcd a b)))
(defn p005 []
  (reduce lcm (range 1 21)))

(time (println (p005)))
