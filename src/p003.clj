;; #003
;; largest prime factor of 600851475143

(use '[util :only (factorize)])

(def N 600851475143)

(defn p003 []
  (->> (factorize N)
       (map (fn [[n e]] n))
       (apply max)))

(time (println (p003)))
