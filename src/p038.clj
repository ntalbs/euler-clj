;; #038
;; What is the largest 1 to 9 pandigital 9-digit number
;; that can be formed as the concatenated product of an integer
;; with (1,2, ... , n) where n > 1?

(ns p038)

(defn pandigital? [sn]
  (= "123456789" (apply str (sort sn))))

(defn prod [n]
  (loop [i 1 acc ""]
    (if (or (< 9 i) (<= 9 (count acc)))
      (apply str acc)
      (recur (inc i) (concat acc (str (* n i)))))))

; The number of digits of multiplicand(M) should be less than or equal to 4.
; M x 1 = dddd
; M x 2 = ddddd

(defn p038 []
  (->> (range 9999 1 -1)
       (map prod)
       (drop-while #(not (pandigital? %)))
       first))

(defn solve []
  (time (println (p038))))
