(ns util
  (:require [clojure.contrib.lazy-seqs :refer (primes)]))

(defn parse-int [s]
  (Integer/parseInt s))

(defn digits
  "Retruns the list of digits of n."
  [n]
  (loop [n n acc '()]
    (if (= n 0)
      acc
      (recur (quot n 10) (conj acc (int (rem n 10)))))))

(defn- add-to-first
  "Returns a new list with its first element is (+ x (first ls)). "
  [ls x]
  {:pre [(list? ls)]}
  (let [fv (first ls)]
    (if (nil? fv)
      nil
      (conj (rest ls) (+ fv x)))))

(defn- canonicalize-digits
  "Returns a canonicalized sequence of digits.
  Canonicalized here means that every digit in the sequence is single digit."
  [ds]
  (loop [ds (reverse ds), acc '()]
    (let [cur (first ds)
          rst (rest ds)
          d10 (quot cur 10)
          d1 (rem cur 10)]
      (if (empty? rst)
        (if (= d10 0)
          (conj acc d1)
          (recur (list d10) (conj acc d1)))
        (recur (add-to-first rst d10) (conj acc d1))))))

(defn- lpad [ds cnt]
  (concat (repeat cnt 0) ds))

(defn add-digits [dv1 dv2]
  (let [cnt1 (count dv1)
        cnt2 (count dv2)
        dv1 (if (< cnt1 cnt2) (lpad dv1 (- cnt2 cnt1)) dv1)
        dv2 (if (< cnt1 cnt2) dv2 (lpad dv2 (- cnt1 cnt2)))
        added (reverse (map + dv1 dv2))]
    (->> (reduce (fn [acc x]
                   (if (empty? acc)
                     (list (quot x 10) (rem x 10))
                     (let [d10 (quot (+ x (first acc)) 10), d1 (rem (+ x (first acc)) 10)]
                       (conj (conj (rest acc) d1) d10))))
                 '()
                 added)
         (drop-while #(zero? %)))))

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
        :else (nil? (first (filter #(= 0 (mod n %))
                                     (range 5 (inc (int (Math/sqrt n))) 2))))))

(defn factorize
  "Returns a sequence of pairs of prime factor and its exponent."
  [n]
  (loop [n n, ps primes, acc []]
    (let [p (first ps)]
      (if (<= p n)
        (if (= 0 (mod n p))
          (recur (quot n p) ps (conj acc p))
          (recur n (rest ps) acc))
        (->> (group-by identity acc)
             (map (fn [[k v]] [k (count v)])))))))

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
