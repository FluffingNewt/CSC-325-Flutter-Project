# Build and Deployment Phase Notes

## Approach
For the CI/CD pipeline's build and deployment steps, I consolidating the two steps into a single .yml file named `pipeline.yml`. This approach effectively resolved several issues created from the use of separate runners, eliminating the need to transfer artifacts between them. Moreover, I opted to use GitHub's branch method for deploying to GitHub Pages.

To create a GitHub Page Documentation, refer to:  
[GitHub Pages Documentation](https://docs.github.com/en/pages/getting-started-with-github-pages/configuring-a-publishing-source-for-your-github-pages-site)

## Challenges
Initially, I attempted to separate the build and deployment phases into two distinct jobs. However, this approach led to the issues mentioned earlier. Thus, I reverted to a unified approach, housing both phases under the `Build & Deploy Phase` job.

Additionally, to enable the app to deploy without error, __line 17__ in `./wordle/web,index.html` needed to be commented out:

```html
<base href="$FLUTTER_BASE_HREF">