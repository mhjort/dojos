(ns poker-test
  (:use [clojure.test]
        [poker]))

(deftest recognizes-high-card
  (let [hand #{[2 :h] [4 :d] [6 :c] [8 :s] [10 :h]}]
    (is (= :high-card (rank-hand hand)))))

(deftest recognizes-one-pair
  (let [hand #{[2 :h] [2 :d] [6 :c] [8 :s] [10 :h]}]
    (is (= :one-pair (rank-hand hand)))))

(deftest recognizes-two-pair
  (let [hand #{[2 :h] [2 :d] [8 :c] [8 :s] [10 :h]}]
    (is (= :two-pair (rank-hand hand)))))

(deftest recognizes-three-of-a-kind
  (let [hand #{[2 :h] [2 :d] [2 :c] [8 :s] [10 :h]}]
    (is (= :three-of-a-kind (rank-hand hand)))))

(deftest recognizes-straight
  (let [hand #{[2 :h] [3 :d] [4 :c] [5 :s] [6 :h]}]
    (is (= :straight (rank-hand hand)))))

(deftest recognizes-straight-when-ace-the-highest
  (let [hand #{[10 :h] [11 :d] [12 :c] [13 :s] [1 :h]}]
    (is (= :straight (rank-hand hand)))))

(deftest recognizes-straight-when-ace-the-lowest
  (let [hand #{[5 :h] [4 :d] [3 :c] [2 :s] [1 :h]}]
    (is (= :straight (rank-hand hand)))))

(deftest recognizes-flush
  (let [hand #{[5 :h] [10 :h] [3 :h] [2 :h] [1 :h]}]
    (is (= :flush (rank-hand hand)))))

(deftest recognizes-full-house
  (let [hand #{[5 :c] [5 :h] [5 :d] [2 :h] [2 :s]}]
    (is (= :full-house (rank-hand hand)))))
