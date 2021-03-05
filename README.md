{::options parse_block_html="true" /}

# k8s-mastodon-bot

<div class="container">
    <label for="config">Your config.edn:</label>
    <textarea name="config" id="config" rows="40" cols="150">
{:transform [{:source {:source-type :twitter
                        ;; optional, defaults to false
                        :include-replies? false
                        ;; optional, defaults to false
                        :include-rts? false
                        ;; Replace Twitter links by Nitter
                        :nitter-urls? false
                        ;; accounts you wish to mirror
                        :accounts ["arstechnica" "WIRED"]}
                :target {:target-type :mastodon
                      ;; optional flag specifying wether the name of the account
                      ;; will be appended in the post, defaults to false
                      :append-screen-name? false
                      ;; optional visibility flag: direct, private, unlisted, public
                      ;; defaults to public
                      :visibility "unlisted"
                      ;; optional boolean to mark content as sensitive. Defaults to true.
                      :sensitive? true
                      ;; optional boolean defaults to false
                      ;; only sources containing media will be posted when set to true
                      :media-only? true
                      ;; optional limit for the post length. Defaults to 300.
                      :max-post-length 300
                      ;; optional signature for posts. Defaults to "not present".
                      :signature "#newsbot"}
                    }]}
      </textarea><br><br>
    <label for="auth">Your auth.edn:</label>
    <textarea name="auth" id="auth" rows="40" cols="150">
{:auth {;; add Twitter config to mirror Twitter accounts
        :twitter {:consumer_key "XXXX"
                  :consumer_secret "XXXX"
                  :access_token_key "XXXX"
                  :access_token_secret "XXXX"}
        :mastodon {:access_token "XXXX"
                  ;; account number you see when you log in and go to your profile
                  ;; e.g: https://mastodon.social/web/accounts/294795
                  :account-id "XXXX"
                  :api_url "https://botsin.space/api/v1/"}
        :tumblr {:consumer_key "XXXX"
                :consumer_secret "XXXX"
                :token "XXXX"
                :token_secret "XXXX"}}}
      </textarea><br><br>
    <button type="button" id="generate-button">Generate k8s yaml</button>
  </div>
  <div id="k8s-mastodon-bot-output">
    <label for="output">Your k8s deployment.yaml:</label>
    <textarea name="output" id="output" rows="40" cols="150">
      </textarea>

  </div>
  <script src="js/main.js"></script>
