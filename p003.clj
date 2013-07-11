;; #003
;; largest prime factor of 600851475143

(def N 600851475143)

(defn factorize [n]
  (let [bound (Math/sqrt n)
        fact (fn [m i acc]
               (if (< i bound)
                 (if (= 0 (mod m i))
                   (recur (quot m i) i (conj acc i))
                   (recur m (inc i) acc))
                 acc))]
    (->> (fact n 2 '())
         (group-by identity)
         (map (fn [[k c]] [k (count c)])))))

(def p003
  (->> (factorize N)
       (map (fn [[n e]] n))
       (apply max)))

(time (println p003))
