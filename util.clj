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

(defn prime?
  "Returns true if n is prime."
  [n]
  (cond (= n 1) false
        (< n 4) true                    ; 2, 3
        (even? n) false
        (< n 9) true                    ; 5, 7
        (= 0 (mod n 3)) false
        :else (empty? (take 1 (filter
                               #(= 0 (mod n %))
                               (range 3 (inc (int (Math/sqrt n))) 2))))))

(defn factorize
  "Returns a sequence of pairs of prime factor and its exponent."
  [n]
  (letfn [(fact [m i acc]
            (let [m-is-prime (prime? m)] ; if m is prime, doesn't need to go further. just append it to acc.
              (if (and (< i n) (not m-is-prime))
                (if (= 0 (mod m i))
                  (recur (quot m i) i (conj acc i))
                  (recur m (inc i) acc))
                (let [xs (->> (group-by identity (if m-is-prime (conj acc m) acc)) 
                              (map (fn [[p ps]] [p (count ps)])))]
                  (if (empty? xs) (list [n 1]) xs)))))]
    (fact n 2 [])))

(defn divisor?
  "Returns true if x is a divisor of n."
  [x n] (zero? (rem n x)))

;; (defn proper-divisors
;;   "Returns the proper divisors of n."
;;   [n]
;;   (filter (fn [x] (divisor? x n)) (range 1 (inc (quot n 2)))))

(defn proper-divisors
  "Returns the proper divisors of n."
  [n]
  (let [bound (inc (int (Math/sqrt n)))]
    (conj (->> (range 2 bound)
               (mapcat (fn [i]
                         (if (divisor? i n)
                           (let [q (quot n i)]
                             (if (not= i q) [i (quot n i)] [i])))))
               (filter (complement nil?))
               sort)
          1)))

(defn sum-of-proper-divisor
  "Returns the sum of n's proper divisors."
  [n]
  (apply + (proper-divisors n)))
