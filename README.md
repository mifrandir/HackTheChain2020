# HackTheChain
Our contribution to the HackTheChain hackathon hosted by MLH in May 2020.

## Blockchain Data Structure

- represent the block tree in memory
- represent the block tree on the hard drive
- sync memory and hard drive

## Miner

- record transactions as received from the server
- after a certain time without a new block or a certain number of transactions in the upcoming block, start mining this block
- if a new block has been received, discard processed transactions and start again
- mining is done by hashing the block and checking whether the hash fits a certain criterion and otherwise changing (i.e. incrementing) the nonce

## Networking

- manage peer pool (know adresses and active peers)
- manage recorded transactions
- keep the chain updated
- broadcast available information to peers
- ask peers for information

## TODO

- blockchain data structure
- peer to peer network
- RSA implementation
- UI