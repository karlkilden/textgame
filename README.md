## Interactive Fiction implemented in java 13.

### Backlog ###

- [x] First few commits
- [x] Get Junit 5 running with Mockito
- [ ] ~~Get PiTest mutationtesting running~~ _Could not get it running, unstable integration with Junit5_
- [x] Game needs to be able to read text. Hardcoding text in the code for now get's messy fast. Select and implement file reading
- [ ] Display simle first Event with a few options for the player.
- [ ] Decide on temporary user feedback method

### Architecure ###
- Hjson is selected as lib for managing read data. Meaning, the internal format will be Hjson as default. Hjson has built-in apis for rich data interaction such as types, defaultValues etc. And translating all that to other structures would be cumbersome and not add anything besides a boundary. Since Hjson is a small library we not contain it, but let it be used in all layers and modules as replacing hjson could be done fairly easy and Hjsons interal structure allows for various migration paths.

- Testing will be Junit latest and Mockito latest. No superseeding framework was found.
