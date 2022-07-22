(ns p014)

;; 첫 시도
(defn collatz1 [n]
  (loop [n n, acc []]
    (if (= 1 n)
      (conj acc 1)
      (if (even? n)
        (recur (quot n 2) (conj acc n))
        (recur (+ (* 3 n) 1) (conj acc n))))))

(defn solve1 []
  (->> (range 1 1000000)
       (map (fn [n] (collatz1 n)))
       (apply max-key (fn [xs] (count xs)))
       first))

;; 시퀀스 대신 개수만 저장
(defn collatz2 [n]
  (loop [n n, cnt 0]
    (if (= 1 n)
      (inc cnt)
      (if (even? n)
        (recur (quot n 2) (inc cnt))
        (recur (+ (* 3 n) 1) (inc cnt))))))

(defn solve2 []
  (->> (range 1 1000000)
       (map (fn [n] [n (collatz2 n)]))
       (apply max-key second)
       first))

;; memoize
(defn collatz3 [n]
  (if (= n 1)
    1
    (if (even? n)
      (inc (collatz3 (quot n 2)))
      (inc (collatz3 (+ (* 3 n) 1))))))

(def collatz3 (memoize collatz3))

(defn solve3 []
  (->> (range 1 1000000)
       (map (fn [n] [n (collatz3 n)]))
       (apply max-key second)
       first))

(defn solve []
  (solve2))
