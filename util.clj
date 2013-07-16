(ns util)

(defn factorial
  "Returns the factorial of n."
  [n]
  (->> (range 1 (inc n))
       (map #(java.math.BigInteger. (str %)))
       (apply *)))

(defn digits
  "Retruns the list of digits of n."
  [n]
  (letfn [(digits-acc [n acc]
            (if (< n 10) (conj acc n)
                (recur (quot n 10) (conj acc (mod n 10)))))]
    (digits-acc n '())))

(defn factorize
  "Returns a sequence of pairs of prime factor and its exponent."
  [n]
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
