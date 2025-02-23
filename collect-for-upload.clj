#!/usr/bin/bb

;; SPDX-FileCopyrightText: 2025 Wong Yi Xiong <https://github.com/TheRealMintd>
;;
;; SPDX-License-Identifier: MPL-2.0

(require '[clojure.string :as str]
         '[babashka.fs :as fs])

(def working-folder (fs/absolutize (first *command-line-args*)))
(def export-folder (fs/path working-folder "Export"))
(def upload-folder (fs/path working-folder "Upload"))

(def edited-files (fs/list-dir export-folder "*.jpg"))
(def edited-file-names (set (map #(str/lower-case (fs/file-name %)) edited-files)))

(def to-upload (->> (fs/list-dir working-folder "*.jpg")
                    (filter #(nil? (edited-file-names (str/lower-case (fs/file-name %)))))
                    (concat edited-files)))

(when-not (fs/exists? upload-folder)
  (fs/create-dir upload-folder))
(run! #(fs/create-link (fs/path upload-folder (fs/file-name %)) %) to-upload)
