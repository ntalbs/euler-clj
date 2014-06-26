(ns util)

(defn parse-int [s]
  (Integer/parseInt s))

(defn digits
  "Retruns the list of digits of n."
  [n]
  (loop [n n acc '()]
    (if (< n 10) (conj acc n)
        (recur (quot n 10) (conj acc (mod n 10))))))

(defn divisor?
  "Returns true if x is a divisor of n."
  [x n] (zero? (rem n x)))

(defn gcd
  "Returns the greatest common divisor of a and b."
  [a b] (if (= b 0) a (recur b (rem a b))))

(defn lcm
  "Returns the least common multiplier of a and b."
  [a b] (/ (* a b) (gcd a b)))

(defn factorial
  "Returns the factorial of n."
  [n] (apply *' (range 1 (inc n))))

(defn perfect-square?
  "Returns true if n is perfect number which means sqrt(n) is intger."
  [n]
  (let [r (int (Math/sqrt n))]
    (= n (* r r))))

(defn pow
  "Compute x^n."
  [x n] (reduce *' (repeat n x)))

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
  (loop [m n, i 2, acc []]
    (let [m-is-prime (prime? m)] ; if m is prime, doesn't need to go further. just append it to acc.
      (if (and (< i n) (not m-is-prime))
        (if (= 0 (mod m i))
          (recur (quot m i) i (conj acc i))
          (recur m (inc i) acc))
        (let [xs (->> (group-by identity (if m-is-prime (conj acc m) acc))
                      (map (fn [[p ps]] [p (count ps)])))]
          (if (empty? xs) (list [n 1]) xs))))))

(defn phi
  "Returns the number of the positive integers less than or equal to n
   that are relatively prime to n.
   Aka Euler's totient of phi function."
  ([p k]
     (letfn [(power [p k] (apply * (repeat k p)))]
       (* (power p k) (- 1 (/ 1 p)))))
  ([n]
     (->> (factorize n)
          (map (fn [[p k]] (phi p k)))
          (apply *))))

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

(defn- valid?
  "returns true if ops is valid sequence of expression. used internally in calc macro. "
  [ops]
  (and (odd? (count ops))
       (->> ops
            rest
            (take-nth 2)
            (apply =))))

(defmacro infix
  "transform then given infix arithematic expresssion to prefix and compute it."
  [ops]
  (if (coll? ops)
    (if (valid? ops)
      (cons (second ops) (for [x (take-nth 2 ops)] `(infix ~x)))
      (throw (Exception. "Invalid expression.")))
    ops))
