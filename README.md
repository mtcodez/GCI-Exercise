### Mante Luo <mante.luo@gmail.com> github.com/mtcodez
## Spring Boot + Jersey
Spring Boot is very easy to config and that's why I put jax-rs with it. As for Jersey, it the standard in the open source world.

## REST APIs
Root is at /api/contracts
- GET     /api/contracts/list # get all contract regardless the type
- GET     /api/contracts/lineitems
- GET     /api/contracts/lineitems/{id}
- POST    /api/contracts/lineitems
- POST    /api/contracts/lineitems/list
- PUT     /api/contracts/lineitems/{id}
- DELETE  /api/contracts/lineitems/{id}

The same is for service order and services agreement, just replace lineitems to the corresponding plural.

For site and invoice
- GET     /api/contracts/invoices
- GET     /api/contracts/invoices/{id}
- PUT     /api/contracts/invoices/{id}
- DELETE  /api/contracts/invoices/{id}
- GET     /api/contracts/{id}/invoices # get all invoices within the contract
- POST    /api/contracts/{id}/invoices # create an invoice out of the contract

- GET     /api/contracts/sites
- GET     /api/contracts/sites/{id}
- PUT     /api/contracts/sites/{id}
- DELETE  /api/contracts/sites/{id}

## Warnings and Monthly
- GET /api/contracts/warnings # including all contracts and invoices
- GET /api/contracts/total # total monthly without services agreements

## Search
After we can the contract id, we just use /api/contracts/{id}/invoices to get all invoices.