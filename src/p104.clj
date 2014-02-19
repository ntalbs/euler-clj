;; #104

(use '[util :only [parse-int digits pow]])

(defn fibo [[a b]] [b (+' a b)])

(defn first-9-digits [n]
  (parse-int (apply str (take 9 (str n)))))

(defn last-9-digits [n]
  (mod n 1000000000))

(defn pandigital? [n]
  (= [1 2 3 4 5 6 7 8 9] (sort (digits n))))

(defn not-pandigital? [sn]
  (not (pandigital? sn)))

(defn p104 []
  (->> (iterate fibo [1 1])
       (map-indexed (fn [i e] [(inc i) e]))
       (drop-while (fn [[_ [a b]]]
                     (or (not-pandigital? (last-9-digits a))
                         (not-pandigital? (first-9-digits a)))))
       first
       first))

(time (println (p104)))
