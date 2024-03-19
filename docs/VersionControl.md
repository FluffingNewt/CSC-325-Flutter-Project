## Source Code Version Control Tools

### Introduction
Version control plays a crucial role in software development by ensuring the integrity, history, and collaboration aspects of a project. It allows multiple developers to work on the same codebase simultaneously and allows for rollbacks to previous versions, providing a centralized platform for managing code changes.

### Version Control System Used
For the project, I have chosen Git as the version control system. Git was selected primarily due to its widespread adoption, branching and merging capabilities, and extensive community support. Additionally, its integration with popular platforms such as GitHub enhances collaboration and code management.

### Repository Setup
- **Structure**: My repository follows a feature-based branching strategy, where each feature or task is developed on a separate branch before being merged into the `main` branch. The directory layout organizes codebase components logically for ease of navigation. For example, all DevContainer files are located in one directory and markdown documents in another.


- **Integration**: The repository is integrated with the DevContainer and CI/CD pipeline using Git actions. This ensures that code changes are automatically tested, built, and deployed upon push or merge events.


- **Standards**: When a big update is pushed, a conventional commit message format is used in the repository to allow for a clear and structured commit history. Branch naming conventions follow a pattern of feature/bugfix/task.

### Common Commands and Usage
- `git clone [repository-url]`: Clone a repository to your local machine.
- `git add .`: Stage all changes for commit.
- `git commit -m "commit message"`: Commit staged changes with a descriptive message.
- `git push origin [branch-name]`: Push committed changes to the remote repository.
- `git pull`: Fetch and merge changes from the remote repository to the local branch.
- `git merge [branch-name]`: Merge changes from a specified branch into the current branch.
- `git checkout -b [new-branch-name]`: Create and switch to a new branch.
- `git revert [commit-hash]`: Revert specific commits by applying an inverse patch.

- VS Code's GitHub integrations/features are also used to operate any repository changes (commit, push, pull, etc.)
    - https://code.visualstudio.com/docs/sourcecontrol/overview.

### Collaboration Features
Git allowed for easy collaboration through features such as:
- **Pull Requests**: Initiates code review and discussion with a your team before merging changes into the main branch.

- **Code Reviews**: Creates a peer review process to ensure code quality and consistency relative to the team.

- **Conflict Resolution**: Provides tools for resolving merge conflicts that may arise when integrating divergent code changes into the main branch.

### Challenges and Solutions
Initially, I faced difficulties in structuring commit messages and branches to maintain a clean and informative history. To overcome this, and described previously, I implemented guidelines for commit message formatting, emphasizing clear and descriptive messages. In addition, I refined my branching strategy to ensure logical organization and traceability of changes throughout the project's development lifecycle.

### Conclusion
In conclusion, Git serves as an indispensable tool for the project, facilitating seamless collaboration, code management, and version control. Its robust features, coupled with best practices and effective workflows, contribute to the overall success and efficiency of the development process.

