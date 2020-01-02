#### Cherry pick
Cherry picking is very useful when you have single/multiple commits to be placed at different branhes
```
git cherry-pick <hash-code-commit>
```
#### squash and rebase
Ever wondered making dumb multiple commits to be squased to a single commit?
```
git rebase -i HEAD~N
```
where N is the number of commits you want to squash. -i is an interactive mode which will allow which all commits that need to be picked (those commits will be there as such), squash(those commits will be squashed to one).
