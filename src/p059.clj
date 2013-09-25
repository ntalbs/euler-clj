;; #059

(use '[clojure.string :only (split trim)])

(defn parse-int [s] (Integer/parseInt s))

(def encrypted-message
  (->> (split (slurp "data/cipher1.txt") #",")
       (map trim)
       (map parse-int)))

(defn decipher [encrypted key]
  (let [key (mapcat (fn [v] v) (repeat (map int key)))]
    (->> (map bit-xor encrypted key)
         (map char)
         (apply str))))

; not complete ...
; working ...
