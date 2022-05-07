(ns p001_test
  (:require [clojure.test :refer :all]
            [p001 :refer :all]))

(deftest p001
  (testing "p001"
    (is (= 233168 (solve)))))
